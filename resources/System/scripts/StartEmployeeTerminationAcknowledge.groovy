import com.egis.data.Form
import com.egis.data.party.Group
import com.egis.data.party.User
import com.egis.DocumentModel
import com.egis.kernel.db.DbManager
import com.egis.kernel.web.WebServer
import com.egis.kernel.Kernel
import com.egis.party.GroupService
import com.egis.transnet.sap.Employee
import com.egis.utils.ValidationException
import com.egis.utils.ValidationUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

Logger log = LoggerFactory.getLogger('com.egis.transnet')
DbManager db = Kernel.get(DbManager.class)
GroupService gs = Kernel.get(GroupService.class)

String groupName = 'Compensation Officer'
Group group = db.resolve(Group.class, groupName)
ValidationUtils.notNull(group, "${groupName} group does not exist")

def users = []
group.users.each { users << it }
if (users.size() < 1) {
    throw new ValidationException("There are no users in the ${groupName} group")
}
Collections.shuffle(users)
User user = users[0]
Employee employee = Employee.find(user.login)

DocumentModel doc = doc
employee.assignToRole(doc, "_officer_")
doc.workflow_status = 'officer'
doc.save()

Map newValues = [
        'sap_no': doc.sap_no,
        'full_name': doc.full_name,
        'email': doc.email,
        'designation': doc.designation,
        'business': doc.business,
        'officer_sap_no': employee.sap_no,
        'officer_name': employee.name,
        'officer_email': employee.email,
        'officer_designation': employee.designation,
        'officer_business': employee.business
]

DocumentModel form = doc.session.spawnForm(db.resolve(Form.class, "Employee Termination Acknowledge Letter"))
form.metadata().set(newValues)
form.signature().setDefaultValues(newValues)

String url = Kernel.get(WebServer.class).publicUrl
url = url + "/public/file/${form.docId.toString()}/Employee Termination Acknowledge Letter.pdf"
form.perm_link = url

form.ownership().assign(user)
form.save()