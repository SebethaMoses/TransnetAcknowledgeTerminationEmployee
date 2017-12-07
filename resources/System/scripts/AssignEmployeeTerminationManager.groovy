import com.egis.DocumentModel
import com.egis.kernel.Kernel
import com.egis.kernel.web.WebServer
import com.egis.transnet.sap.Employee

DocumentModel doc = doc
Employee.get(doc.sap_no).findManager().assignToRole(doc, "_manager_")
doc.workflow().set('Permlink', Kernel.get(WebServer.class).publicUrl
        + "/public/file/${doc.docId.toString()}/Employee Termination Resignation Letter.pdf")