package com.desert.gym.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private int status; // Código HTTP do erro (ex: 400, 404, 500)
    private String message; // Mensagem de erro
    private LocalDateTime timestamp; // Data e hora em que o erro ocorreu
    private String path; // Endpoint onde o erro ocorreu (opcional)
    private String error; // Tipo do erro (ex: "Bad Request", "Internal Server Error")

    public ErrorResponse(int status, String message, String path, String error) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.path = path;
        this.error = error;
    }

    // Getters e Setters (importantes para a serialização JSON)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}