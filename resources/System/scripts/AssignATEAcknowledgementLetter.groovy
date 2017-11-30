import com.egis.data.party.User
import com.egis.DocumentModel
import com.egis.party.GroupService
import com.egis.kernel.db.DbManager
import com.egis.kernel.Kernel

DocumentModel doc = doc
DbManager db = Kernel.get(DbManager.class)
GroupService gs = Kernel.get(GroupService.class)

String group = 'HCM'
if (doc.role_level == 'C') {
    group = 'COAM'
} else if (doc.role_level == 'D to F') {
    group = 'GM HR'
}

String name = gs.getMembers(group, null)[0]
doc.signature().assignRole('_manager_', name)
doc.workflow().set('_manager_', name)
doc.parties().add(db.resolve(User.class, name))