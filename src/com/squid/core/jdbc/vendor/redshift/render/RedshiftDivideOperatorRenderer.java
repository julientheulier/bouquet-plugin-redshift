package com.squid.core.jdbc.vendor.redshift.render;
/**
 * Fix for T3094
 */
import com.squid.core.domain.operators.ExtendedType;
import com.squid.core.domain.operators.OperatorDefinition;
import com.squid.core.sql.db.render.DivideOperatorRenderer;
import com.squid.core.sql.render.RenderingException;
import com.squid.core.sql.render.SQLSkin;

public class RedshiftDivideOperatorRenderer extends  DivideOperatorRenderer {

	/*
	@Override
	protected boolean isExactNumber(ExtendedType ext) {
		return ext.isExactNumber() && (ext.getDataType()!= Types.NUMERIC && ext.getDataType()!=Types.DECIMAL);
	}
	 */

	@Override
	protected ExtendedType getExtendedType() {
		return ExtendedType.REAL;
	}

	@Override
	public String prettyPrint(SQLSkin skin, OperatorDefinition opDef, String[] args) throws RenderingException {
		throw new RenderingException();
	}

}
