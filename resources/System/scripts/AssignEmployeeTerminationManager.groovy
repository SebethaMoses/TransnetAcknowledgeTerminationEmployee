import com.egis.DocumentModel
import com.egis.transnet.sap.Employee
import com.egis.utils.ValidationUtils

DocumentModel doc = doc
Employee manager = Employee.get(doc.sap_no).findManager()
ValidationUtils.notNull(manager, "Cannot find manager for employee with SAP no $doc?.sap_no")
manager.assignToRole(doc, "_manager_")
doc.save()