import com.egis.data.party.User
import com.egis.DocumentModel
import com.egis.party.GroupService
import com.egis.kernel.db.DbManager
import com.egis.kernel.Kernel

GroupService gs = Kernel.get(GroupService.class)
DbManager db = Kernel.get(DbManager.class)
DocumentModel doc = doc

String name = gs.getMembers('Compensation Officer', null)[0]
doc.signature().assignRole('_officer_', name)
doc.workflow().set('_officer_', name)
doc.parties().add(db.resolve(User.class, name))
doc.workflow_status = 'officer'
doc.save()