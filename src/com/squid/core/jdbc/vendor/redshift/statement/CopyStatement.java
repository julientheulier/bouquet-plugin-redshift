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
package com.squid.core.jdbc.vendor.redshift.statement;

import com.squid.core.database.model.Table;

/**
 * definition of the copy statement
 * check http://docs.aws.amazon.com/redshift/latest/dg/r_COPY.html for information
 * @author sergefantino
 *
 */
public class CopyStatement {
	
	public enum Compression {
		NONE,GZIP,LZOP
	}
	
	public static final String DATE_FORMAT_AUTO = "auto";
	public static final String TIME_FORMAT_AUTO = "auto";
	public static final String TIME_FORMAT_EPOCHSECS = "epochsecs";
	public static final String TIME_FORMAT_EPOCHMILLISECS = "epochmillisecs";
	
	private Table target;
	private String fromURL = null;
	private AWSCredentials credentials = null;
	private Character delimiter = null;
	private boolean CSV = false;
	private Character quote = null;
	private String region = null;
	private boolean manifest = false;
	private boolean encrypted = false;
	private Compression compression = Compression.NONE;
	private boolean removeQuotes = false;
	private Character acceptInvChars = null;
	private int maxError = 0;
	private String dateFormat = null;
	private String timeFormat = null;
	private int ignoreHeader = 0;
	private boolean acceptAnyDate = false;
	private boolean ignoreBlankLines = false;
	private boolean truncateColumns = false;
	private boolean fillRecord = false;
	private boolean trimBlanks = false;
	private String nullAs = null;
	private boolean emptyAsNull = false;
	private boolean blankAsNull = false;
	private boolean escape = false;
	private boolean roundec = false;
	
	public CopyStatement(Table target, String fromURL) {
		this.target = target;
		this.fromURL = fromURL;
	}

	public Table getTarget() {
		return target;
	}

	public void setTarget(Table target) {
		this.target = target;
	}

	public String getFromURL() {
		return fromURL;
	}

	public void setFromURL(String fromURL) {
		this.fromURL = fromURL;
	}

	public AWSCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(AWSCredentials credentials) {
		this.credentials = credentials;
	}

	public Character getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(Character delimiter) {
		this.delimiter = delimiter;
	}

	public boolean isCSV() {
		return CSV;
	}

	public void setCSV(boolean cSV) {
		CSV = cSV;
	}

	public Character getQuote() {
		return quote;
	}

	public void setQuote(Character quote) {
		this.quote = quote;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public boolean isManifest() {
		return manifest;
	}

	public void setManifest(boolean manifest) {
		this.manifest = manifest;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	public Compression getCompression() {
		return compression;
	}

	public void setCompression(Compression compression) {
		this.compression = compression;
	}

	public boolean isRemoveQuotes() {
		return removeQuotes;
	}

	public void setRemoveQuotes(boolean removeQuotes) {
		this.removeQuotes = removeQuotes;
	}

	public Character getAcceptInvChars() {
		return acceptInvChars;
	}

	public void setAcceptInvChars(Character acceptInvChars) {
		this.acceptInvChars = acceptInvChars;
	}

	public int getMaxError() {
		return maxError;
	}

	public void setMaxError(int maxError) {
		this.maxError = maxError;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public int getIgnoreHeader() {
		return ignoreHeader;
	}

	public void setIgnoreHeader(int ignoreHeader) {
		this.ignoreHeader = ignoreHeader;
	}

	public boolean isAcceptAnyDate() {
		return acceptAnyDate;
	}

	public void setAcceptAnyDate(boolean acceptAnyDate) {
		this.acceptAnyDate = acceptAnyDate;
	}

	public boolean isIgnoreBlankLines() {
		return ignoreBlankLines;
	}

	public void setIgnoreBlankLines(boolean ignoreBlankLines) {
		this.ignoreBlankLines = ignoreBlankLines;
	}

	public boolean isTruncateColumns() {
		return truncateColumns;
	}

	public void setTruncateColumns(boolean truncateColumns) {
		this.truncateColumns = truncateColumns;
	}

	public boolean isFillRecord() {
		return fillRecord;
	}

	public void setFillRecord(boolean fillRecord) {
		this.fillRecord = fillRecord;
	}

	public boolean isTrimBlanks() {
		return trimBlanks;
	}

	public void setTrimBlanks(boolean trimBlanks) {
		this.trimBlanks = trimBlanks;
	}

	public String getNullAs() {
		return nullAs;
	}

	public void setNullAs(String nullAs) {
		this.nullAs = nullAs;
	}

	public boolean isEmptyAsNull() {
		return emptyAsNull;
	}

	public void setEmptyAsNull(boolean emptyAsNull) {
		this.emptyAsNull = emptyAsNull;
	}

	public boolean isBlankAsNull() {
		return blankAsNull;
	}

	public void setBlankAsNull(boolean blankAsNull) {
		this.blankAsNull = blankAsNull;
	}

	public boolean isEscape() {
		return escape;
	}

	public void setEscape(boolean escape) {
		this.escape = escape;
	}

	public boolean isRoundec() {
		return roundec;
	}

	public void setRoundec(boolean roundec) {
		this.roundec = roundec;
	}

}
