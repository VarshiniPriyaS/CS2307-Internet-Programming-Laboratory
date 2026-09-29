import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String source = request.getParameter("source");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");
        String seat = request.getParameter("seat");
        String fare = request.getParameter("fare");

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ticketdb?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "mysql"
            );

            String sql = "INSERT INTO tickets "
                    + "(passenger_name, source, destination, "
                    + "travel_date, seat_no, fare) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, source);
            ps.setString(3, destination);
            ps.setString(4, date);
            ps.setString(5, seat);
            ps.setDouble(6, Double.parseDouble(fare));

            int result = ps.executeUpdate();

            if (result > 0) {

                out.println("<html>");
                out.println("<head>");
                out.println("<title>Booking Success</title>");

                out.println("<style>");

                out.println("body {");
                out.println("font-family: Arial;");
                out.println("background: linear-gradient(135deg,#667eea,#764ba2);");
                out.println("}");

                out.println(".ticket {");
                out.println("width: 450px;");
                out.println("margin: 70px auto;");
                out.println("padding: 30px;");
                out.println("background: white;");
                out.println("border-radius: 15px;");
                out.println("box-shadow: 0 10px 25px rgba(0,0,0,0.3);");
                out.println("}");

                out.println("h2 {");
                out.println("text-align:center;");
                out.println("color:#4b4b9b;");
                out.println("}");

                out.println("p {");
                out.println("font-size:17px;");
                out.println("padding:8px;");
                out.println("border-bottom:1px solid #ddd;");
                out.println("}");

                out.println(".btn {");
                out.println("display:block;");
                out.println("text-align:center;");
                out.println("padding:12px;");
                out.println("margin-top:15px;");
                out.println("background:#667eea;");
                out.println("color:white;");
                out.println("text-decoration:none;");
                out.println("border-radius:6px;");
                out.println("}");

                out.println(".btn2 {");
                out.println("display:block;");
                out.println("text-align:center;");
                out.println("padding:12px;");
                out.println("margin-top:10px;");
                out.println("background:#764ba2;");
                out.println("color:white;");
                out.println("text-decoration:none;");
                out.println("border-radius:6px;");
                out.println("}");

                out.println("</style>");
                out.println("</head>");

                out.println("<body>");

                out.println("<div class='ticket'>");

                out.println("<h2>Ticket Booked Successfully!</h2>");

                out.println("<p><b>Passenger Name:</b> "
                        + name + "</p>");

                out.println("<p><b>Source:</b> "
                        + source + "</p>");

                out.println("<p><b>Destination:</b> "
                        + destination + "</p>");

                out.println("<p><b>Travel Date:</b> "
                        + date + "</p>");

                out.println("<p><b>Seat Number:</b> "
                        + seat + "</p>");

                out.println("<p><b>Fare:</b> Rs. "
                        + fare + "</p>");

                out.println("<a class='btn' href='TicketListServlet'>");
                out.println("View All Tickets");
                out.println("</a>");

                out.println("<a class='btn2' href='index.html'>");
                out.println("Book Another Ticket");
                out.println("</a>");

                out.println("</div>");

                out.println("</body>");
                out.println("</html>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {

            out.println("<html>");
            out.println("<head><title>Booking Failed</title></head>");
            out.println("<body>");

            out.println("<h2>Booking Failed</h2>");
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}