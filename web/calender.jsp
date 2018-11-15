<%-- 
    Document   : calender
    Created on : Nov 13, 2018, 3:34:46 PM
    Author     : tochukwu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8" /> 
        <title>Calendar Widget</title> 
        <link rel="stylesheet" href="./calender.css" /> 
    </head>
    <body>
        <div id="cal"> 
            <div class="header"> 
                <span class="left button" id="prev"> &lang; </span> 
                <span class="left hook"></span> 
                <span class="month-year" id="label"> June 20&0 </span> 
                <span class="right hook"></span> 
                <span class="right button" id="next"> &rang; </span>
            </div> 
            <table id="days"> 
                <tr>
                    <td>sun</td> 
                    <td>mon</td> 
                    <td>tue</td> 
                    <td>wed</td> 
                    <td>thu</td> 
                    <td>fri</td> 
                    <td>sat</td>
                </tr>

            </table> 
            <div id="cal-frame"> 
                <table class="curr"> 
                    <tbody> 
                        <tr><td class="nil"></td><td class="nil"></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td></tr> 
                        <tr><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td class="today">11</td><td>12</td></tr> 
                        <tr><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td></tr> 
                        <tr><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td></tr> 
                        <tr><td>27</td><td>28</td><td>29</td><td>30</td><td class="nil"></td><td class="nil"></td><td class="nil"></td></tr> 
                    </tbody> 
                </table>

            </div> 
        </div>
    </body>
</html>