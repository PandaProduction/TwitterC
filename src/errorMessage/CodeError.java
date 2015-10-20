package errorMessage;

/**
 *  Référentiel des code d'erreur de l'application.
 * @author Mary
 */
public class CodeError {
    public static final int SUCESS = 0, FAILLURE = 500, NOT_IMPLEMENT = 999;
    public static final int DOMAIN_NOT_SUPPORTED = 4001, ENCRYPTION_FAIL = 4002;
    public static final int CONNEXION_FAIL = 5001, STATEMENT_EXECUTE_FAIL = 5002, STATEMENT_CLOSE_FAIL = 5003, STATEMENT_INTEGRITY_CONSTRAINT_VIOLATION = 5004 ; 
}
