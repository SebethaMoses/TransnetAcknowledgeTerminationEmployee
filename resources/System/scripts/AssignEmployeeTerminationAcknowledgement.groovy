import com.egis.data.party.Group
import com.egis.data.party.User
import com.egis.DocumentModel
import com.egis.party.GroupService
import com.egis.kernel.db.DbManager
import com.egis.kernel.Kernel
import com.egis.transnet.sap.Employee
import com.egis.utils.ValidationException
import com.egis.utils.ValidationUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

Logger log = LoggerFactory.getLogger('com.egis.transnet')
DbManager db = Kernel.get(DbManager.class)
GroupService gs = Kernel.get(GroupService.class)

String groupName = 'HCM'
if (doc.role_level == 'C') {
    groupName = 'COAM'
} else if (doc.role_level == 'D to F') {
    groupName = 'GM HR'
}
Group group = db.resolve(Group.class, groupName)
ValidationUtils.notNull(group, "${groupName} group does not exist")

def users = []
group.users.each { users << it }
if (users.size() < 1) {
    throw new ValidationException("There are no users in the ${groupName} group")
}
Collections.shuffle(users)
User user = users[0]
ValidationUtils.notNull(user, "No Compensation Officer user found")
Employee party = Employee.find(user.login)
ValidationUtils.notNull(party, "Employee not found for user login ${user.login}")

DocumentModel doc = doc
party.assignToRole(doc, "_party_")
doc.save()