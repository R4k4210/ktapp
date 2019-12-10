package com.example.ktapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ktapp.R
import com.example.ktapp.contract.LoginContract
import com.example.ktapp.model.Department
import com.example.ktapp.presenter.LoginPresenter
import java.io.Serializable


class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var btn_login : Button
    lateinit var email : TextView
    lateinit var password : TextView
    lateinit var progressBar : ProgressBar
    var departments: List<Department?>? = null

    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = LoginPresenter(this)

        btn_login = findViewById(R.id.btn_login)
        email = findViewById(R.id.txt_email)
        password = findViewById(R.id.txt_password)
        progressBar = findViewById(R.id.progressBar)

        btn_login.setOnClickListener(View.OnClickListener {
            login(email.text.toString(), password.text.toString())
        })
    }

    override fun badCredenctials() {
        Toast.makeText(applicationContext,"Bad Credentials",Toast.LENGTH_SHORT).show()
    }

    override fun emptyEmail() {
        password.error = "Email cannot be blank"
    }

    override fun emptyPassword() {
        password.error = "Password cannot be blank"
    }

    override fun error() {
        Toast.makeText(applicationContext,"Connection error, please try again later",Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun goToDepartmentActivity(department: List<Department?>) {
        departments = department
        val intent = Intent(this@LoginActivity, DepartmentsActivity::class.java)
        intent.putExtra("departments", departments as Serializable)
        startActivity(intent)
    }

    override fun login(email: String, password: String) {
        presenter?.validateCredentials(email, password)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

}
