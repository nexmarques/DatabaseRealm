package devandroid.nex.aularealm

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import devandroid.nex.aularealm.database.DatabaseRealm
import devandroid.nex.aularealm.databinding.ActivityMainBinding
import devandroid.nex.aularealm.model.Usuario
import io.realm.kotlin.types.ObjectId


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate( layoutInflater )
    }

    private val database by lazy {
       DatabaseRealm()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.btnSalvar.setOnClickListener{
            val nome = binding.editNome.text.toString()
            val usuario = Usuario()
            usuario.nome = nome
            usuario.idade = 27
            database.salvar(usuario)
            binding.editNome.text.clear()
        }
        binding.btnListar.setOnClickListener {
            val lista = database.listar()
            var textoLista = ""
            lista.forEach { list ->
               textoLista += "${list.nome} - idade ${list.idade} \n "
               Log.i("info_realm", "${list.id} - ${list.nome}")
            }
            binding.txtResultado.text = textoLista

        }
        binding.btnRemover.setOnClickListener{

            val id = ObjectId.from("676183bb2dd2af36315c6787")
            database.remover(id)
        }
        binding.btnAtualizar.setOnClickListener {
            val idSelecionado = ObjectId.from("676183fa11f9214c2823551d")

            val nomeRecuperado = binding.editNome.text.toString()
            val usuario = Usuario().apply {
                this.id = idSelecionado
                this.nome = nomeRecuperado
                this.idade = 40
            }
            database.atualizar(usuario)
        }
    }
}