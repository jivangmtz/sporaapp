package com.ivan.garcia.sporaapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivan.garcia.core.realm.User
import com.mastercard.sonic.widget.SonicView
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.query
/*import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myUser = User()
        myUser.age = 20
        myUser.name = "José"
        myUser.height = 1.70f

        val sonicView: SonicView? = null
    }

    fun realmTest() {
        val config = RealmConfiguration.Builder(schema = setOf(Task::class)).build()

        val realm: Realm = Realm.open(config)
        realm.writeBlocking {
            copyToRealm(Task().apply {
                name = "Do work"
                status = "Open"
            })
        }

        // all tasks in the realm
        val tasks: RealmResults<Task> = realm.query<Task>().find()

        // tasks in the realm whose name begins with the letter 'D'
        val tasksThatBeginWIthD: RealmResults<Task> =
            realm.query<Task>("name BEGINSWITH $0", "D")
                .find()
        val openTasks: RealmResults<Task> =
            realm.query<Task>("status == $0", "Open")
                .find()
        // change the first task with open status to in progress status
        realm.writeBlocking {
            findLatest(openTasks[0])?.status = "In Progress"
        }
        // delete the first task in the realm
        realm.writeBlocking {
            val writeTransactionTasks = query<Task>().find()
            delete(writeTransactionTasks.first())
        }
    }
}