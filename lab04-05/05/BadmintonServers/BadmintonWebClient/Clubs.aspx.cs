using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using BadmintonWebClient.BadmintonServiceReference;

namespace BadmintonWebClient
{
    public partial class Clubs : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            Service1Client client = new Service1Client();
            BadmintonClub[] clubs = client.getBadmintonClubs();

            BadmintonClubsGridView.DataSource = clubs;
            BadmintonClubsGridView.DataBind();
        }
    }
}