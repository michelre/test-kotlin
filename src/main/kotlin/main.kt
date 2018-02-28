package main

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.map
import com.github.salomonbrys.kotson.*
import com.google.gson.Gson

fun main(args: Array<String>) {
    getRepositories()
}

fun getRepositories(): Request {
    return Fuel.get("https://api.github.com/users/michelre/repos").responseString { _, _, result ->
        print(result)
        /*deserializeRepositories(result).fold(success = {
            print(it)
        }, failure = {})*/
    }
}

fun deserializeRepositories(repositories: Result<String, FuelError>): Result<List<Repository>, FuelError> {
    val gson = Gson()
    return repositories.map { gson.fromJson<List<Repository>>(it) }
}

class Repository(val id: String, val name: String) {}
