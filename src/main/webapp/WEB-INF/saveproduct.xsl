<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:param name="category" />
	<xsl:param name="subcategory" />
	<xsl:param name="name" />
	<xsl:param name="producer" />
	<xsl:param name="model" />
	<xsl:param name="dateOfIssue" />
	<xsl:param name="color" />
	<xsl:param name="price" select="'no price'" />
	<xsl:param name="product" />




	<xsl:template match="@* | node()">
		<xsl:copy>
			<xsl:apply-templates select="@* | node()" />
		</xsl:copy>
	</xsl:template>


	<xsl:template
		match="shop/categories/category[$category]/subcategories/subcategory[$subcategory]/product[1]">
		
		<product>
			<name>
				<xsl:value-of select="$name" />
			</name>
			<producer>
				<xsl:value-of select="$producer" />
			</producer>
			<model>
				<xsl:value-of select="$model" />
			</model>
			<dateOfIssue>
				<xsl:value-of select="$dateOfIssue" />
			</dateOfIssue>
			<color>
				<xsl:value-of select="$color" />
			</color>
			<xsl:choose>
				<xsl:when test="not($price = 'no price')">
					<price>
						<xsl:value-of select="$price" />
					</price>
				</xsl:when>
				<xsl:otherwise>
					<notInStock />
				</xsl:otherwise>
			</xsl:choose>
		</product>
		<xsl:copy>
			<xsl:apply-templates select="@* | node()" />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>