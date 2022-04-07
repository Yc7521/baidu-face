package cn.edu.njuit.web.server.service;

import cn.edu.njuit.web.server.domain.ValidateCode;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class ValidateCodeService {
    public static HashMap<String, String> ValidateCodeStore = new HashMap<>();
    private final Op[] ops = {Op.ADD, Op.SUB, Op.MUL, Op.DIV};

    public ValidateCode createValidate() {
        Random random = new Random();
        //随机码
        String randomCode = UUID.randomUUID().toString();
        //随机操作
        Op op = ops[random.nextInt(ops.length)];
        //两个随机数
        int number1 = random.nextInt(100);
        int number2 = random.nextInt(100);
        if (number1 < number2) {
            int temp = number1;
            number1 = number2;
            number2 = temp;
        }
        //算式
        String imageText = "%d%s%d=?".formatted(number1, op.op, number2);
        //答案
        int result = 0;
        switch (op) {
            case ADD:
                result = number1 + number2;
                break;
            case SUB:
                result = number1 - number2;
                break;
            case MUL:
                result = number1 * number2;
                break;
            case DIV:
                result = number1 / number2;
                break;
        }
        //存储到store中，随机码作为键，算式和答案作为值，用逗号隔开
        ValidateCodeStore.put(randomCode, "%s,%d".formatted(imageText, result));

        return new ValidateCode(randomCode);
    }

    public boolean validate(String randomCode, String validateCode) {
        if (ValidateCodeStore.containsKey(randomCode)) {
            String validateString = ValidateCodeStore.get(randomCode);
            String answer = validateString.split(",")[1];
            //校验一次，无论对错立即失效
            ValidateCodeStore.remove(randomCode);
            return answer.equals(validateCode);
        }
        return false;
    }

    public String getValidateText(String randomCode) {
        if (ValidateCodeStore.containsKey(randomCode)) {
            String validateString = ValidateCodeStore.get(randomCode);
            return validateString.split(",")[0];
        }
        return null;
    }

    enum Op {
        ADD("+"), SUB("-"), MUL("*"), DIV("/");

        final String op;

        Op(String op) {
            this.op = op;
        }
    }
}
