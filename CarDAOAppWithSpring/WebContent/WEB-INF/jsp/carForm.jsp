<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <p><a href="controller?action=viewCarList">[Return to List]</a></p>
  
  <form method="post" action="controller">
  <input type="hidden" name="action" value="saveCar" />
  <input type="hidden" name="id" value="${car.id}<%-- Set this value to id property of car attribute --%>" />

  <table>
    <!-- input fields -->
      <td><input type="text" name="make"  value="${car.make}<%-- Set this value to make property of car attribute --%>" /></td>
    </tr>  
    <tr>  
      <td><input type="text" name="model" value="${car.model}<%-- Set this value to model property of car attribute --%>" /></td>
    </tr>
    <tr>
      <td><input type="text" name="modelYear" value="${car.modelYear}<%-- Set this value to modelYear property of car attribute --%>" /></td>
    </tr>
    
    <!-- Save/Reset buttons -->
    <tr>
      <td colspan="2">
        <input type="submit" name="save" value="Save" /> 
        &nbsp;&nbsp;
        <input type="reset" name="reset" value="Reset" />
      </td>
    </tr>                
  </form>