import com.egis.DocumentModel
import com.egis.kernel.Kernel
import com.egis.kernel.web.WebServer
import com.egis.transnet.sap.Employee
import com.egis.utils.ValidationUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

Logger log = LoggerFactory.getLogger('com.egis.transnet')

DocumentModel doc = doc
Employee employee = Employee.getLoggedIn()
Employee manager = employee.findManager()
ValidationUtils.notNull(manager, "Cannot find manager for the employee that is logged in")
String url = Kernel.get(WebServer.class).publicUrl
url = url + "/public/file/${doc.docId.toString()}/Employee Termination Resignation Letter.pdf"

Map newValues = [
        'sap_no': employee.sap_no,
        'full_name': employee.name,
        'email': employee.email,
        'designation': employee.designation,
        'business': employee.business,
        'manager_sap_no': manager.sap_no,
        'manager_name': manager.name,
        'manager_email': manager.email,
        'manager_designation': manager.designation,
        'perm_link': url
]
log.debug("Employee Termination prepopulate: $newValues")
doc.metadata().set(newValues)
doc.signature().setDefaultValues(newValues)
doc.save()