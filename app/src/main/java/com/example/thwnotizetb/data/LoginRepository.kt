package com.example.thwnotizetb.data

import com.example.thwnotizetb.data.model.LoggedInUser



class LoginRepository(val dataSource: LoginDataSource) {

    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        user = null
    }

    fun logout() {
        null.also { user = it }
        dataSource.logout()
    }

    fun login(username: String): Result<LoggedInUser> {

        val result = dataSource.login(username)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
    }
}