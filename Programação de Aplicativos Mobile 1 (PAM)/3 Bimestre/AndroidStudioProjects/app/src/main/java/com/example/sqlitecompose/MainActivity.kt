package com.example.sqlitecompose

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    // --- Data model ---
    data class Note(
        val id: Long? = null,
        val title: String,
        val content: String,
        val pessoa: String
    )

    // --- SQLite Helper ---
    class DBHelper(context: Context) :
        SQLiteOpenHelper(context, "app.db", null, 2) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(
                """
                CREATE TABLE notes(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    content TEXT NOT NULL,
                    pessoa TEXT NOT NULL
                )
                """.trimIndent()
            )
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS notes")
            onCreate(db)
        }

        fun insertNote(note: Note): Long {
            val cv = ContentValues().apply {
                put("title", note.title)
                put("content", note.content)
                put("pessoa", note.pessoa)
            }
            return writableDatabase.insert("notes", null, cv)
        }

        fun updateNote(note: Note): Int {
            requireNotNull(note.id) { "ID não pode ser nulo para update" }
            val cv = ContentValues().apply {
                put("title", note.title)
                put("content", note.content)
                put("pessoa", note.pessoa)
            }
            return writableDatabase.update(
                "notes",
                cv,
                "id=?",
                arrayOf(note.id.toString())
            )
        }

        fun deleteNote(id: Long): Int {
            return writableDatabase.delete(
                "notes",
                "id=?",
                arrayOf(id.toString())
            )
        }

        fun getAllNotes(): List<Note> {
            val list = mutableListOf<Note>()
            val c: Cursor = readableDatabase.rawQuery(
                "SELECT id, title, content, pessoa FROM notes ORDER BY id DESC",
                null
            )
            c.use { cur ->
                val idIdx = cur.getColumnIndexOrThrow("id")
                val titleIdx = cur.getColumnIndexOrThrow("title")
                val contentIdx = cur.getColumnIndexOrThrow("content")
                val pessoaIdx = cur.getColumnIndexOrThrow("pessoa")
                while (cur.moveToNext()) {
                    list.add(
                        Note(
                            id = cur.getLong(idIdx),
                            title = cur.getString(titleIdx),
                            content = cur.getString(contentIdx),
                            pessoa = cur.getString(pessoaIdx)
                        )
                    )
                }
            }
            return list
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = DBHelper(this)

        setContent {
            MaterialTheme {
                Surface(Modifier.fillMaxSize()) {
                    NotesScreen(dbHelper = db)
                }
            }
        }
    }

    // --- UI Compose + CRUD ---
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun NotesScreen(dbHelper: DBHelper) {
        var notes by remember { mutableStateOf(dbHelper.getAllNotes()) }

        var title by remember { mutableStateOf(TextFieldValue("")) }
        var content by remember { mutableStateOf(TextFieldValue("")) }
        var pessoa by remember { mutableStateOf(TextFieldValue("")) }

        var editingId by remember { mutableStateOf<Long?>(null) }

        fun clearFields() {
            title = TextFieldValue("")
            content = TextFieldValue("")
            pessoa = TextFieldValue("")
            editingId = null
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(if (editingId == null) "Notas (SQLite + Compose)" else "Editando #$editingId")
                    }
                )
            }
        ) { padding ->
            Column(
                Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Conteúdo") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = pessoa,
                    onValueChange = { pessoa = it },
                    label = { Text("Pessoa") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(Modifier.height(12.dp))

                Row {
                    Button(
                        onClick = {
                            val t = title.text.trim()
                            val c = content.text.trim()
                            val p = pessoa.text.trim()
                            if (t.isEmpty() || c.isEmpty() || p.isEmpty()) return@Button

                            if (editingId == null) {
                                dbHelper.insertNote(Note(title = t, content = c, pessoa = p))
                            } else {
                                dbHelper.updateNote(
                                    Note(id = editingId, title = t, content = c, pessoa = p)
                                )
                            }
                            notes = dbHelper.getAllNotes()
                            clearFields()
                        }
                    ) {
                        Text(if (editingId == null) "Salvar" else "Atualizar")
                    }
                    Spacer(Modifier.width(8.dp))
                    OutlinedButton(onClick = { clearFields() }) {
                        Text("Limpar")
                    }
                }

                Spacer(Modifier.height(16.dp))
                HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
                Spacer(Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 80.dp)
                ) {
                    items(notes, key = { it.id ?: -1 }) { note ->
                        NoteItem(
                            note = note,
                            onClick = {
                                editingId = note.id
                                title = TextFieldValue(note.title)
                                content = TextFieldValue(note.content)
                                pessoa = TextFieldValue(note.pessoa)
                            },
                            onDelete = { id ->
                                dbHelper.deleteNote(id)
                                notes = dbHelper.getAllNotes()
                                if (editingId == id) clearFields()
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun NoteItem(
        note: Note,
        onClick: () -> Unit,
        onDelete: (Long) -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { onClick() },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = note.title, style = MaterialTheme.typography.titleMedium)
                    note.id?.let {
                        TextButton(onClick = { onDelete(it) }) {
                            Text("Excluir")
                        }
                    }
                }
                Spacer(Modifier.height(4.dp))
                Text(text = note.content, style = MaterialTheme.typography.bodyMedium)
                Spacer(Modifier.height(6.dp))
                Text(text = "Pessoa: ${note.pessoa}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}
