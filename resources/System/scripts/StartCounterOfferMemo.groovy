import com.egis.data.Form
import com.egis.DocumentModel
import com.egis.kernel.db.DbManager
import com.egis.kernel.Kernel

DbManager db = Kernel.get(DbManager.class)
DocumentModel doc = doc

Map newValues = [
        'employee_sap_no': doc.sap_no,
        'employee_name': doc.full_name,
        'employee_email': doc.email,
        'arl_formNo': doc.formNo
]

DocumentModel form = doc.session.spawnForm(db.resolve(Form.class, "Counter Offer Memo Form"))
form.metadata().set(newValues)
form.signature().setDefaultValues(newValues)

form.ownership().assign(doc.session.user)
form.links().add(doc)
form.allocate('CounterOffer')
form.save()