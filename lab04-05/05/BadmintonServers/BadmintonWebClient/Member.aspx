<%@ Page Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="Member.aspx.cs" Inherits="BadmintonWebClient.Member" %>
<asp:Content ContentPlaceHolderID="MainContent2" runat="server">
    <section>
        <asp:Label ID="LabelMember" runat="server" Text="Give club id:"></asp:Label>
        <asp:TextBox ID="TextBoxMember" runat="server"></asp:TextBox>
        <asp:Button ID="ButtonMember" runat="server" Text="OK" OnClick="ButtonMember_Click" />
    </section>
    <asp:GridView ID="GridViewMembers" runat="server"></asp:GridView>
</asp:Content>