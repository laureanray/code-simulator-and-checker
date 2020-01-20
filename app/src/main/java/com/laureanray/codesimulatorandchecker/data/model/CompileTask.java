package com.laureanray.codesimulatorandchecker.data.model;

public class CompileTask {
    private String source;
    private String result;
    private String input;
    private boolean compileResult;
    private String compileMessage;

    public CompileTask(String source, String result, String input, boolean compileResult, String compileMessage) {
        this.source = source;
        this.result = result;
        this.input = input;
        this.compileResult = compileResult;
        this.compileMessage = compileMessage;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean getCompileResult() {
        return compileResult;
    }

    public void setCompileResult(boolean compileResult) {
        this.compileResult = compileResult;
    }

    public String getCompileMessage() {
        return compileMessage;
    }

    public void setCompileMessage(String compileMessage) {
        this.compileMessage = compileMessage;
    }
}
