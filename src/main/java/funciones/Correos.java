package funciones;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import io.github.cdimascio.dotenv.Dotenv;
import javax.swing.JOptionPane;

public class Correos {
    private final Dotenv dotenv = Dotenv.load();
    private final String resendKey = dotenv.get("RESEND_KEY");

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
        enviarCorreo(correo, body, "Credenciales");

    }

    public void correoCredencialesDocentes(String nombre, String apellido, String user, String password,
            String correo) {
        String body = String.format(
                """
                        <p> Hola <strong>%s %s</strong> nos alegra que hayas decidio formar parte de nuestro equipo</p>

                         <p>Tus credenciales para acceder al sistema son las siguientes</p>
                         <p>Usuario: <strong>%s</strong></p>
                         <p>Contraseña: <strong>%s</strong></p>

                         <p>Esperamos que sigas creciendo con nosotros</p>
                              """, nombre, apellido, user, password);
        enviarCorreo(correo, body, "Credenciales");

    }

    public void correoInscripcion(String curso, String nombre, String apellido, String correo) {
        String body = String.format(
            """
                   <h2> Acabas de realizar una inscripcion al curso de %s</h2>

                   <p>  Hola <strong>%s %s</strong> tu inscripción se ha realizado con éxito</p>

                   <p> Esperamos verte pronto </p>
                    """,
            curso, nombre, apellido);
        
        enviarCorreo(correo, body, "Inscripcion");
    }

    private void enviarCorreo(String correo, String body, String subject) {
        if (correo == null) {
            System.err.println("Fallo al enviar correo: destinatario es null.");
            JOptionPane.showMessageDialog(null,
                    "No se pudo enviar el correo porque la dirección de correo es nula.",
                    "Error de correo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        correo = correo.trim();
        if (correo.isEmpty()) {
            System.err.println("Fallo al enviar correo: destinatario está vacío.");
            JOptionPane.showMessageDialog(null,
                    "No se pudo enviar el correo porque la dirección de correo está vacía.",
                    "Error de correo", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Resend resend = new Resend(resendKey);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("SistemaUes <noreply@sistemaues.me>")
                .to(correo)
                .subject(subject)
                .html(body)
                .build();

        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println("Correo enviado a: " + correo + "; id: " + data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
            System.err.println("Fallo al enviar correo a: " + correo + " - " + e.getMessage());
            JOptionPane.showMessageDialog(null,
                    "No se pudo enviar el correo: " + e.getMessage(),
                    "Error de correo", JOptionPane.ERROR_MESSAGE);
        }
    }
}
