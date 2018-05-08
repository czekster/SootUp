/* Soot - a J*va Optimization Framework
 * Copyright (C) 1999 Patrick Lam
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */






package de.upb.soot.jimple;

import java.util.ArrayList;
import java.util.List;

import de.upb.soot.basic.AbstractStmt;
import de.upb.soot.basic.RetStmt;
import de.upb.soot.basic.Value;
import de.upb.soot.basic.ValueBox;

public class JRetStmt extends AbstractStmt implements RetStmt
{
    final ValueBox stmtAddressBox;
    //List useBoxes;


    protected JRetStmt(ValueBox stmtAddressBox)
    {
            this.stmtAddressBox = stmtAddressBox;

    }



    public Value getStmtAddress()
    {
        return stmtAddressBox.getValue();
    }

    public ValueBox getStmtAddressBox()
    {
        return stmtAddressBox;
    }

    public void setStmtAddress(Value stmtAddress)
    {
        stmtAddressBox.setValue(stmtAddress);
    }

    public List<ValueBox> getUseBoxes()
    {
        List<ValueBox> useBoxes = new ArrayList<ValueBox>();

        useBoxes.addAll(stmtAddressBox.getValue().getUseBoxes());
        useBoxes.add(stmtAddressBox);

        return useBoxes;
    }

    public boolean fallsThrough(){return true;}        
    public boolean branches(){return false;}

}
