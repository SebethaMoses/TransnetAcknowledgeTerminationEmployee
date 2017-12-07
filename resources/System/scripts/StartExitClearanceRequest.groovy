import com.egis.data.Form
import com.egis.DocumentModel
import com.egis.kernel.db.DbManager
import com.egis.kernel.Kernel

DocumentModel doc = doc
DbManager db = Kernel.get(DbManager.class)

Form form = doc.session.spawnForm(db.resolve(Form.class, "Request Exit Clearance"))
form.ownership().assign(doc.ownership().owner)
form.links().add(doc)
form.allocate('RequestExitClearance')