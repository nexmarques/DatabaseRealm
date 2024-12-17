package devandroid.nex.aularealm.database

import devandroid.nex.aularealm.model.Usuario
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.ObjectId

class DatabaseRealm {

    val config = RealmConfiguration.create(schema = setOf(Usuario::class))
    private val realm: Realm = Realm.open(config)

    fun salvar( usuario: Usuario ){
        realm.writeBlocking {
            copyToRealm( usuario )
        }
    }

    fun listar(): RealmResults<Usuario>{
        val lista = realm.query<Usuario>().find()
        return lista
    }

    fun remover(id: ObjectId){
        realm.writeBlocking {
            val usuarioRemovido = query<Usuario>("id == $0", id )
                .find()
                .first()
            delete( usuarioRemovido )
        }
    }
    fun atualizar( usuario: Usuario ){
        realm.writeBlocking {
            val usuarioAtualizado = query<Usuario>("id == $0", usuario.id)
                .find()
                .first()
            usuarioAtualizado.nome = usuario.nome
            usuarioAtualizado.idade = usuario.idade
        }

    }
}