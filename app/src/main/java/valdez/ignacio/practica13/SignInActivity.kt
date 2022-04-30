package valdez.ignacio.practica13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import valdez.ignacio.practica13.objetosNegocio.*

class SignInActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val extras = intent.extras
        val email = extras?.get("usuario") as FirebaseUser

        val tv_holamundo = findViewById<TextView>(R.id.saludo)

        if(email != null){
            tv_holamundo.text = "¡Hola "+email.email+"!"
        }else{
            tv_holamundo.text = "algo salió mal :("
        }

    }
}