package valdez.ignacio.practica13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import valdez.ignacio.practica13.objetosNegocio.*

class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var et_email = findViewById<EditText>(R.id.emailEditText)
        var et_password = findViewById<EditText>(R.id.passwordEditText)
        var btn_login = findViewById<Button>(R.id.signInAppCompatButton)

        btn_login.setOnClickListener{
            val email = et_email.text.toString().trim()
            val password = et_password.text.toString().trim()
            val user = signIn(email, password)
            if(user != null){
                reload(user)
            }
        }


    }

    private fun reload(user:FirebaseUser){
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra("usuario",user)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    private fun signIn(email:String, password:String):FirebaseUser?{
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ Task ->
            if(Task.isSuccessful){
                Log.d("TAG", "signInWithEmail:success")
            }else{
                Log.w("TAG", "signInWithEmail:failure")
                Toast.makeText(baseContext, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
        return auth.currentUser
    }
}