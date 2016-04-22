/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.server.command;

import java.io.IOException;

/**
 *
 * @author User
 */
public interface UndoableCommand extends Command{
    public void undo() throws IOException;
}
