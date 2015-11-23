using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using BadmintonWebClient.BadmintonServiceReference;

namespace BadmintonWebClient
{
    public partial class Member : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void ButtonMember_Click(object sender, EventArgs e)
        {
            String id = TextBoxMember.Text;
            if (id != null)
            {
                int clubId;
                try
                {
                    clubId = int.Parse(id);
                }
                catch
                {
                    clubId = 0;
                }

                Service1Client client = new Service1Client();
                BadmintonClubMember[] badmintonClubMembers = client.getBadmintonClubMembers(clubId);
                GridViewMembers.DataSource = badmintonClubMembers;
                GridViewMembers.DataBind();

            }
        }
    }
}