package library.entities;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Book implements Serializable {

    private String titLe;
    private String author;
    private String callNo;
    private int id;

    private enum State {AVAILABLE, ON_LOAN, DAMAGED, RESERVED}
    private State state;


	public Book(String author, String title, String callNo, int id) {
        this.author = author;
        this.titLe = title;
        this.callNo = callNo;
        this.id = id;

        this.state = State.AVAILABLE;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book: ").append(id).append("\n")
                .append("  Title:  ").append(titLe).append("\n")
                .append("  Author: ").append(author).append("\n")
                .append("  CallNo: ").append(callNo).append("\n")
                .append("  State:  ").append(state);

        return sb.toString();
    }

    public Integer getID() {
        return id;
    }

    public String getTitLe() {
        return titLe;
    }

    public boolean is_Available() {
        return state == State.AVAILABLE;
    }

    public boolean is_On_Loan() {
        return state == State.ON_LOAN;
    }

    public boolean is_Damaged() {
        return state == State.DAMAGED;
    }

    public void borrow() {


        if (state.equals(State.AVAILABLE))
            state = State.ON_LOAN;

        else
            throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));

    }

    public void isReturned(boolean State) {

        if (state.equals(state.ON_LOAN))
            if (is_Damaged())
                state = state.DAMAGED;

            else
                state = state.AVAILABLE;


        else
            throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));

    }

    public void repair() {

        if (state.equals(state.DAMAGED))
            state = state.AVAILABLE;

        else
            throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));

    }
}
