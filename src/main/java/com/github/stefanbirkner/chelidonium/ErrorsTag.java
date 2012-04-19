package com.github.stefanbirkner.chelidonium;

import javax.servlet.jsp.JspException;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * The errors tag displays errors for a particular field or object using an
 * error alert. Set the closeable attribute to "true" if you want a cross for
 * dismissing the alert. If you want more padding, then set the block attribute
 * to "true".
 * 
 * <p>
 * This tag supports three main usage patterns:
 * 
 * <ol>
 * <li>Field only - set '<code>path</code>' to the field name (or path)</li>
 * <li>Object errors only - omit '<code>path</code>'</li>
 * <li>All errors - set '<code>path</code>' to '<code>*</code>'</li>
 * </ol>
 * 
 * 
 * @since 0.1.0
 */
public class ErrorsTag extends
		org.springframework.web.servlet.tags.form.ErrorsTag {
	private static final String BASE_CSS_CLASS = "alert alert-error";

	private static final long serialVersionUID = 3631530119078622259L;

	private boolean closeable = false;

	public ErrorsTag() {
		setCssClass(BASE_CSS_CLASS);
	}

	public void setBlock(boolean block) {
		if (block)
			setCssClass(BASE_CSS_CLASS + " alert-block");
	}

	public void setCloseable(boolean closeable) {
		this.closeable = closeable;
	}

	@Override
	protected void renderDefaultContent(TagWriter tagWriter)
			throws JspException {
		for (String message : getBindStatus().getErrorMessages())
			writeErrorMessage(tagWriter, message);
	}

	protected void writeErrorMessage(TagWriter tagWriter, String message)
			throws JspException {
		tagWriter.startTag("div");
		writeDefaultAttributes(tagWriter);
		if (closeable)
			writeClosingCross(tagWriter);
		tagWriter.appendValue(getDisplayString(message));
		tagWriter.endTag();
	}

	private void writeClosingCross(TagWriter tagWriter) throws JspException {
		tagWriter.startTag("a");
		tagWriter.writeAttribute("class", "close");
		tagWriter.writeAttribute("data-dismiss", "alert");
		tagWriter.appendValue("×");
		tagWriter.endTag();
	}

	@Override
	protected String resolveId() throws JspException {
		Object id = evaluate("id", getId());
		if (id == null) {
			return null;
		} else {
			String idString = id.toString();
			return (StringUtils.hasText(idString) ? idString : null);
		}
	}
}
