import com.egis.data.party.User
import com.egis.kernel.Kernel
String name = Kernel.get('com.egis.party.GroupService').getMembers('Compensation Officer', null)[0]
doc.signature().assignRole('_officer_', name)
doc.workflow().set('_officer_', name)
doc.parties.add(Kernel.get('com.egis.kernel.db.DbManager').resolve(User.class, name))