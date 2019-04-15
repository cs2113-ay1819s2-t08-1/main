/*
 * Copyright (c) 2011, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package seedu.address.ui.password;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Checks if password is correct
 */
public class ValidatePassword {

    private static Label message = new Label("");
    private static final String MESSAGE_VALID_LOGIN = "Login Successful";
    private static final String MESSAGE_INVALID_LOGIN = "Your password is incorrect!";
    private static int tries = 5;
    private static String password = "password";
    /**
     *
     */
    public static void display(boolean []user) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Group root = new Group();
        Scene scene = new Scene(root, 260, 150);
        window.setScene(scene);
        window.setTitle("VolunCHeer");
        window.setOnCloseRequest(e -> System.exit(2));
        window.resizableProperty().setValue(false);

        VBox vbPassword = new VBox();
        vbPassword.setPadding(new Insets(50, 0, 0, 20));
        vbPassword.setSpacing(10);


        HBox hbPassword = new HBox();
        hbPassword.setSpacing(10);
        hbPassword.setAlignment(Pos.CENTER_LEFT);

        Label labelPassword = new Label("Password");
        final PasswordField checkPw = new PasswordField();

        checkPw.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (checkPw.getText().equals(password)) {
                    message.setText(MESSAGE_VALID_LOGIN);
                    message.setTextFill(Color.web("green"));
                    user[0] = true;
                    window.close();
                } else {
                    message.setText(MESSAGE_INVALID_LOGIN + "\nNumber of attempts left: " + tries);
                    message.setTextFill(Color.web("red"));

                    tries--;
                    if (tries == 0) {
                        System.exit(2);
                    }
                }
                checkPw.setText("");
            }
        });

        hbPassword.getChildren().addAll(labelPassword, checkPw);
        vbPassword.getChildren().addAll(hbPassword, message);

        scene.setRoot(vbPassword);
        window.showAndWait();
    }
}
