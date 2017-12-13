import com.egis.data.Form
import com.egis.DocumentModel
import com.egis.kernel.db.DbManager
import com.egis.kernel.Kernel

DbManager db = Kernel.get(DbManager.class)
DocumentModel doc = doc

DocumentModel form = doc.session.spawnForm(db.resolve(Form.class, "Counter Offer Memo Form"))

def meta = form.metadata()
meta.set('employee_sap_no': doc.sap_no)
meta.set('employee_name': doc.fullname)
meta.set('employee_email': doc.email)
form.signature().setDefaultValues(meta.all)

form.ownership().assign(doc.session.user)
form.links().add(doc)
form.allocate('CounterOffer')