package de.upb.swt.soot.java.bytecode.frontend.apk.dexpler;

/*-
 * #%L
 * Soot - a J*va Optimization Framework
 * %%
 * Copyright (C) 1997 - 2018 Raja Vallée-Rai and others
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import de.upb.swt.soot.core.jimple.common.stmt.JGotoStmt;
import de.upb.swt.soot.core.jimple.common.stmt.JIfStmt;
import de.upb.swt.soot.core.jimple.common.stmt.Stmt;
import de.upb.swt.soot.core.model.Body;
import de.upb.swt.soot.core.transform.BodyInterceptor;


import javax.annotation.Nonnull;

/**
 * Transformer for reducing goto chains. If there is a chain of jumps in the code before the final target is reached, we
 * collapse this chain into a direct jump to the target location.
 *
 * @author Steven Arzt
 *
 */
public class DexJumpChainShortener implements BodyInterceptor {


  @Override
  public void interceptBody(@Nonnull Body.BodyBuilder builder) {
    for (Stmt stmt : builder.getStmts()) {

      if (stmt instanceof JGotoStmt) {
        JGotoStmt jGotoStmtstmt = (JGotoStmt) stmt;
        while (jGotoStmtstmt.getTarget() instanceof JGotoStmt) {
          JGotoStmt nextTarget = (JGotoStmt) jGotoStmtstmt.getTarget();
          jGotoStmtstmt.setTarget(nextTarget.getTarget());
        }
      } else if (stmt instanceof JIfStmt) {
        JIfStmt jIfStmt = (JIfStmt) stmt;
        while (jIfStmt.getTarget() instanceof JGotoStmt) {
          JGotoStmt nextTarget = (JGotoStmt) jIfStmt.getTarget();
          jIfStmt.setTarget(nextTarget.getTarget());
        }
      }
    }
  }
}