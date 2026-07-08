package delegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(private val key: String, private val defaultValue: T) : ReadWriteProperty<Any?, T> {
    private val prefs = mutableMapOf<String, T>()

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        val value = prefs[key] ?: defaultValue
        println("Getting value for key '$key': $value")
        return value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        prefs[key] = value
        println("Setting value for key '$key' to $value")
    }
}

class UserSettings {
    var username by Preference("username", "defaultUser")
}

fun main() {
    val settings = UserSettings()
    println(settings.username)
    settings.username = "newUser"
    println(settings.username)
    
//    Getting value for key 'username': User
//    User
//    Setting value for key 'username' to newUser
//    Getting value for key 'username': newUser
//    newUser
}
