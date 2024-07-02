package service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class UsuarioService {
	
	public static void validarLogin() {
	}
	
	public static boolean textVacio(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        return paramValue == null || paramValue.trim().isEmpty();
    }
	
	public static String errores(Map<String, String> errores, String fieldName) {
        if (errores.containsKey(fieldName)) {
            return errores.get(fieldName);
        } else {
        	return "";
        }
    }
	
	public static String recuerda(HttpServletRequest request, String name, String submit) {
        // Verifica si se ha enviado el formulario con el parámetro 'submit'
        if (request.getParameter(submit) != null) {
            // Obtiene el valor del parámetro 'name' de la solicitud
            String value = request.getParameter(name);
            if (value != null && !value.isEmpty()) {
                return value; // Devuelve el valor si no está vacío
            }
        }
        return ""; // Devuelve una cadena vacía si no se recuerda ningún valor
    }
	
	public static boolean validaRegistro(HttpServletRequest request, Map<String, String> errores) {
        // Validar nombre
        if (textVacio(request, "nombre")) {
            errores.put("nombre", "Nombre vacío");
        }

        // Validar apellidos
        if (textVacio(request, "apellidos")) {
            errores.put("apellidos", "Apellidos vacíos");
        }

        // Validar contraseña
        if (textVacio(request, "clave")) {
            errores.put("clave", "Contraseña vacía");
        }

        // Validar repetición de contraseña
        if (textVacio(request, "claveRep")) {
            errores.put("claveRep", "Repite la contraseña introducida");
        } else if (!request.getParameter("clave").equals(request.getParameter("claveRep"))) {
            errores.put("claveRep", "Las contraseñas no coinciden");
        }

        // Validar email
        if (textVacio(request, "email")) {
            errores.put("email", "Email vacío");
        }
        
        // Validar fecha
        if (textVacio(request, "fecha_nacimiento")) {
            errores.put("fecha", "Fecha de nacimiento vacía");
        }

        // Verificar si se encontraron errores
        return errores.isEmpty();
    }
	

}
