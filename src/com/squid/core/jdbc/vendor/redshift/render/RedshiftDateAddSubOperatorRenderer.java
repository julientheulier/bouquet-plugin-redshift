/*******************************************************************************
 * Copyright © Squid Solutions, 2016
 *
 * This file is part of Open Bouquet software.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation (version 3 of the License).
 *
 * There is a special FOSS exception to the terms and conditions of the
 * licenses as they are applied to this program. See LICENSE.txt in
 * the directory of this program distribution.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Squid Solutions also offers commercial licenses with additional warranties,
 * professional functionalities or services. If you purchase a commercial
 * license, then it supersedes and replaces any other agreement between
 * you and Squid Solutions (above licenses and LICENSE.txt included).
 * See http://www.squidsolutions.com/EnterpriseBouquet/
 *******************************************************************************/
package com.squid.core.jdbc.vendor.redshift.render;

import com.squid.core.domain.DomainNumericConstant;
import com.squid.core.domain.DomainStringConstant;
import com.squid.core.domain.operators.ExtendedType;
import com.squid.core.domain.operators.OperatorDefinition;
import com.squid.core.jdbc.vendor.redshift.postgresql.render.PostgresDateAddSubOperatorRenderer;
import com.squid.core.sql.render.OperatorPiece;
import com.squid.core.sql.render.RenderingException;
import com.squid.core.sql.render.SQLSkin;

public class RedshiftDateAddSubOperatorRenderer extends PostgresDateAddSubOperatorRenderer {


	public RedshiftDateAddSubOperatorRenderer(OperatorType builtinType) {
		super(builtinType);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getSqlCode(SQLSkin skin, OperatorPiece piece,
			OperatorDefinition opDef, String[] args, OperatorType type) throws RenderingException {
		String txt = super.getSqlCode(skin, piece, opDef, args, type);
		if (args.length==3) {
			ExtendedType[] extendedTypes = getExtendedPieces(piece);
			int unit = new Double(((DomainNumericConstant)extendedTypes[1].getDomain()).getValue()).intValue();
			String period = ((DomainStringConstant)extendedTypes[2].getDomain()).getValue().toUpperCase();
			if ("month".equalsIgnoreCase(period) ||"year".equalsIgnoreCase(period)) {
				if ("year".equalsIgnoreCase(period)) {
					unit = unit * 12;
				}
				txt = "ADD_MONTHS(" + args[0] + ", " + unit + ")";
			}
		}
		return txt;
	}

}
