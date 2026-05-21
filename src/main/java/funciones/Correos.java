package funciones;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;

import io.github.cdimascio.dotenv.Dotenv;

public class Correos {
    Dotenv dotenv = Dotenv.load();
    String resendKey = dotenv.get("RESEND_KEY");

    public void correoCredencialesEstudiante(String nombre, String apellido, String user, String password,
            String correo) {
        String body = String.format(
                """
                        <p> Hola <strong>%s %s</strong> nos alegra que hayas decidio formar parte de nosotros</p>

                         <p>Tus credenciales para acceder al sistema son las siguientes</p>
                         <p>Usuario: <strong>%s</strong></p>
                         <p>Contraseña: <strong>%s</strong></p>

                         <p>Esperamos que sigas aprendiendo con nosotros</p>
                              """, nombre, apellido, user, password);

        Resend resend = new Resend(resendKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("SistemaUes <noreply@sistemaues.me>")
                .to(correo)
                .subject("Credenciales")
                .html(body)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
