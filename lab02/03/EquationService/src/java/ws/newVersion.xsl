<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:ns2="http://ws/">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="ns2:bepaalNulpunten">
        <ns2:solveQuadratic xmlns:ns2="http://ws/">
            <xsl:apply-templates/>
        </ns2:solveQuadratic>
    </xsl:template>
    <xsl:template match="a|c0">
        <c0><xsl:value-of select="."/></c0>
    </xsl:template>
    <xsl:template match="b|c1">
        <c1><xsl:value-of select="."/></c1>
    </xsl:template>
    <xsl:template match="c|c2">
        <c2><xsl:value-of select="."/></c2>
    </xsl:template>
</xsl:stylesheet>
