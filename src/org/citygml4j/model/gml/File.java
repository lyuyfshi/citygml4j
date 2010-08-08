package org.citygml4j.model.gml;

import org.citygml4j.builder.copy.Copyable;
import org.citygml4j.commons.child.Child;

public interface File extends GML, Child, Copyable {
	public RangeParameters getRangeParameters();
	public String getFileName();
	public FileValueModel getFileStructure();
	public String getMimeType();
	public String getCompression();
	public boolean isSetRangeParameters();
	public boolean isSetFileName();
	public boolean isSetFileStructure();
	public boolean isSetMimeType();
	public boolean isSetCompression();
	
	public void setRangeParameters(RangeParameters rangeParameters);
	public void setFileName(String fileName);
	public void setFileStructure(FileValueModel fileStructure);
	public void setMimeType(String mimeType);
	public void setCompression(String compression);
	public void unsetRangeParameters();
	public void unsetFileName();
	public void unsetFileStructure();
	public void unsetMimeType();
	public void unsetCompression();
}
