package com.squid.core.jdbc.vendor.redshift.render;

import com.squid.core.domain.operators.OperatorDefinition;
import com.squid.core.sql.db.render.TrimOperatorRenderer;
import com.squid.core.sql.render.OperatorPiece;
import com.squid.core.sql.render.RenderingException;
import com.squid.core.sql.render.SQLSkin;

public class RedshiftTrimOperatorRenderer extends TrimOperatorRenderer {

  public RedshiftTrimOperatorRenderer(String mode) {
    super(mode);
  }

  @Override
  public String prettyPrint(SQLSkin skin, OperatorPiece piece, OperatorDefinition opDef, String[] args) throws RenderingException {
    if (prepend.equals("BOTH")) {
      return super.prettyPrint(skin, piece, opDef, args);
    }
    String principalstring = null;
    String trimCharacter = "' '";
    if (args != null) {
      if (args.length != 1 && args.length != 2) {
        throw new RuntimeException("invalid syntax for trim operator");
      }
      principalstring = args[0];
      if (args.length == 2) {
        trimCharacter = args[1];
      }
    }
    String str = "";
    if (prepend.equals("LEADING")) {
      str += "L";
    }
    if (prepend.equals("TRAILING")) {
      str += "R";
    }
    str += "TRIM(" + principalstring + ", " + trimCharacter + ")";
    return str;
  }

}
