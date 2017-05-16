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

import com.squid.core.database.impl.DataSourceReliable;
import com.squid.core.database.metadata.IMetadataEngine;
import com.squid.core.database.model.DatabaseProduct;
import com.squid.core.domain.extensions.JSON.JSONOperatorDefinition;
import com.squid.core.domain.extensions.date.AddMonthsOperatorDefinition;
import com.squid.core.domain.extensions.date.operator.DateOperatorDefinition;
import com.squid.core.domain.extensions.string.regex.RegexpOperatorDefinition;
import com.squid.core.domain.extensions.string.trim.TrimOperatorDefinition;
import com.squid.core.domain.operators.RankOperatorDefinition;
import com.squid.core.jdbc.vendor.redshift.postgresql.render.ANSIZeroIfNullFeatureSupport;
import com.squid.core.jdbc.vendor.redshift.postgresql.render.PostgresSkinProvider;
import com.squid.core.sql.db.features.IGroupingSetSupport;
import com.squid.core.sql.db.features.IMetadataForeignKeySupport;
import com.squid.core.sql.db.features.IRollupStrategySupport;
import com.squid.core.sql.db.render.AddMonthsOperatorRenderer;
import com.squid.core.sql.db.render.DateAddSubOperatorRenderer;
import com.squid.core.sql.db.render.RegexpOperatorRenderer;
import com.squid.core.sql.db.templates.DefaultJDBCSkin;
import com.squid.core.sql.db.templates.ISkinProvider;
import com.squid.core.sql.db.templates.SkinRegistry;
import com.squid.core.sql.render.ISkinFeatureSupport;
import com.squid.core.sql.render.SQLSkin;
import com.squid.core.sql.render.ZeroIfNullFeatureSupport;

public class RedshiftSkinProvider extends PostgresSkinProvider {

	private static final ZeroIfNullFeatureSupport zeroIfNull = new ANSIZeroIfNullFeatureSupport();

	public RedshiftSkinProvider() {
		super();
		//
		registerOperatorRender(DateOperatorDefinition.DATE_ADD, new RedshiftDateAddSubOperatorRenderer(DateAddSubOperatorRenderer.OperatorType.ADD));
		registerOperatorRender(DateOperatorDefinition.DATE_SUB, new RedshiftDateAddSubOperatorRenderer(DateAddSubOperatorRenderer.OperatorType.SUB));
		registerOperatorRender(AddMonthsOperatorDefinition.ADD_MONTHS, new AddMonthsOperatorRenderer());
		registerOperatorRender(DateOperatorDefinition.DATE_INTERVAL, new RedshiftDateIntervalOperatorRenderer());
		//
		registerOperatorRender(RankOperatorDefinition.ROWNUMBER_ID, new RowNumber());

		registerOperatorRender(JSONOperatorDefinition.JSON_ARRAY_LENGTH, new RedshiftJSONOperatorRenderer());
		registerOperatorRender(JSONOperatorDefinition.JSON_EXTRACT_FROM_ARRAY, new RedshiftJSONOperatorRenderer());
		registerOperatorRender(JSONOperatorDefinition.JSON_EXTRACT_PATH_TEXT, new RedshiftJSONOperatorRenderer());
		//
		registerOperatorRender(TrimOperatorDefinition.STRING_LTRIM, new RedshiftTrimOperatorRenderer("LEADING"));
		registerOperatorRender(TrimOperatorDefinition.STRING_RTRIM, new RedshiftTrimOperatorRenderer("TRAILING"));
		registerOperatorRender(TrimOperatorDefinition.STRING_TRIM, new RedshiftTrimOperatorRenderer("BOTH"));
		//
		registerOperatorRender(RegexpOperatorDefinition.REGEXP_COUNT, new RegexpOperatorRenderer("REGEXP_COUNT"));
		registerOperatorRender(RegexpOperatorDefinition.REGEXP_INSTR, new RegexpOperatorRenderer("REGEXP_INSTR"));
	}

	@Override
	public double computeAccuracy(DatabaseProduct product) {
		try {
			if (product != null) {
				if (IMetadataEngine.REDSHIFT_NAME.equalsIgnoreCase(product.getProductName())) {
					return PERFECT_MATCH;
				} else {
					return NOT_APPLICABLE;
				}
			} else {
				return NOT_APPLICABLE;
			}
		} catch (Exception e) {
			return NOT_APPLICABLE;
		}
	}

	@Override
	public SQLSkin createSkin(DatabaseProduct product) {
		return new RedshiftSQLSkin(this, product);
	}

	@Override
	public String getSkinPrefix(DatabaseProduct product) {
		return "redshift";
	}

	@Override
	public ISkinProvider getParentSkinProvider() {
		return SkinRegistry.INSTANCE.findSkinProvider(PostgresSkinProvider.class);
	}

	@Override
	public ISkinFeatureSupport getFeatureSupport(DefaultJDBCSkin skin, String featureID) {
		if (featureID == IGroupingSetSupport.ID) {
			return IGroupingSetSupport.IS_NOT_SUPPORTED;
		} else if (featureID == IMetadataForeignKeySupport.ID) {
			return ISkinFeatureSupport.IS_SUPPORTED;
		} else if (featureID == DataSourceReliable.FeatureSupport.AUTOCOMMIT) {
			return ISkinFeatureSupport.IS_NOT_SUPPORTED;
		} else if (featureID.equals(IRollupStrategySupport.ID)) {
			return IRollupStrategySupport.OPTIMIZE_USING_TEMPORARY_STRATEGY;
		}
		// else
		return super.getFeatureSupport(skin, featureID);
	}

}
