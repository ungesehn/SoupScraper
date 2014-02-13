<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"
		omit-xml-declaration="no"></xsl:output>
	<!-- GENERELLES TEMPLATE -->

	<xsl:template match="/">
		<document>
			<date>
				<xsl:value-of select="(.//div[@class='gueltigkeit'])[2]" />
			</date>
			<soups>
				<xsl:apply-templates select="//div[@class='wochenkarte']" />
				<xsl:apply-templates select="//div[@id='col3_content']/div[2]" />
			</soups>
		</document>
	</xsl:template>


	<xsl:template match="div[@class='wochenkarte']">
		<xsl:for-each select="div[@class='subcolumns']">
			<xsl:if test="position() != 1">
				<soup>
					<id>
						<xsl:value-of select=".//div[@class='produktnr']" />
					</id>
					<name>
						<xsl:value-of select=".//div[@class='produktname']" />
					</name>
					<text>
						<xsl:value-of select=".//div[@class='produkttext']" />
					</text>
					<category>
						<xsl:value-of select=".//div[@class='produktkategorie']" />
					</category>
					<price>
						<small>
							<xsl:value-of select="substring((.//div[@class='produktpreis'])[1],0,5)" />
						</small>
						<big>
							<xsl:value-of select="substring((.//div[@class='produktpreis'])[2],0,5)" />
						</big>
					</price>
				</soup>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="div[@class='floatbox rechtsmitte']">
		<soup>
			<id>
				<xsl:value-of select="//div/h2/span/span/span" />
			</id>
			<name>
				<xsl:value-of select="//div/h3[2]/span" />
			</name>
			<text></text>
			<category></category>
			<price>
				<small></small>
				<big></big>
			</price>
		</soup>
	</xsl:template>
</xsl:stylesheet>