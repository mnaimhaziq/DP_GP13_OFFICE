package org.office.dp_gp13_office;

import javafx.scene.control.Button;

import java.util.Stack;

public class Toggle {
    private Space space;
    private Stack<Button> stackBtn;
    private Stack<Integer> stackInt;
    private Stack<String> stackStr;
    private Button button;
    private int index;
    private String entityName;
    private boolean visibility;

    public void toggleEntity() {
        visibility = space.toggleEntity(button, index, entityName);
    }


    public void untoggleEntity() {
        if (!stackInt.isEmpty()) {
            space.toggleEntity(stackBtn.pop(), stackInt.pop(), stackStr.pop());
        }
    }


    /**
     * @return Space return the space
     */
    public Space getSpace() {
        return space;
    }

    /**
     * @param space the space to set
     */
    public void setSpace(Space space) {
        this.space = space;
    }

    /**
     * @return Stack<Button> return the stackBtn
     */
    public Stack<Button> getStackBtn() {
        return stackBtn;
    }

    /**
     * @param stackBtn the stackBtn to set
     */
    public void setStackBtn(Stack<Button> stackBtn) {
        this.stackBtn = stackBtn;
    }

    /**
     * @return Stack<Integer> return the stackInt
     */
    public Stack<Integer> getStackInt() {
        return stackInt;
    }

    /**
     * @param stackInt the stackInt to set
     */
    public void setStackInt(Stack<Integer> stackInt) {
        this.stackInt = stackInt;
    }

    /**
     * @return Stack<String> return the stackStr
     */
    public Stack<String> getStackStr() {
        return stackStr;
    }

    /**
     * @param stackStr the stackStr to set
     */
    public void setStackStr(Stack<String> stackStr) {
        this.stackStr = stackStr;
    }

    /**
     * @return Button return the button
     */
    public Button getButton() {
        return button;
    }

    /**
     * @param button the button to set
     */
    public void setButton(Button button) {
        stackBtn.add(button);
        this.button = button;
    }

    /**
     * @return int return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        stackInt.add(index);
        this.index = index;
    }

    /**
     * @return String return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName the entityName to set
     */
    public void setEntityName(String entityName) {
        stackStr.add(entityName);
        this.entityName = entityName;
    }

    /**
     * @return boolean return the visibility
     */
    public boolean isVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

}
