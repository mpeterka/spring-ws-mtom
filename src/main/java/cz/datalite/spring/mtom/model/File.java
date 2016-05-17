package cz.datalite.spring.mtom.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Message with file and content in binary - to send via MTOM.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileType", namespace = "http://datalite.cz/spring/mtom-message/1.0", propOrder = {
		"filename",
		"content"
})
@XmlRootElement(name = "file", namespace = "http://datalite.cz/spring/mtom-message/1.0")
public class File {
	@XmlElement(namespace = "http://datalite.cz/spring/mtom-message/1.0", name = "filename")
	private String filename;
	@XmlElement(namespace = "http://datalite.cz/spring/mtom-message/1.0", name = "content")
	private byte[] content;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
