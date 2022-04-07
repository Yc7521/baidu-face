package cn.edu.njuit.web.server.domain;

public class ValidateCode {
    String randomCode;
    String imageUrl;

    public ValidateCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getImageUrl() {
        this.imageUrl = "/image/validate-code?code=" + this.randomCode;
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
