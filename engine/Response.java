package engine;

public class Response {
    public final static Response correctAnswer = new Response(true, "Congratulations, you're right!");
    public final static Response wrongAnswer = new Response(false, "Wrong answer! Please, try again.");
    private final boolean success;
    private final String feedback;
    public Response(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }
    public boolean isSuccess() {
        return success;
    }
    public String getFeedback() {
        return feedback;
    }

}
