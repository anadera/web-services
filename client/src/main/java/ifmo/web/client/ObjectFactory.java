
package ifmo.web.lab1.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ifmo.web.lab1.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetItems_QNAME = new QName("http://lab1.web.ifmo/", "getItems");
    private final static QName _DeleteItem_QNAME = new QName("http://lab1.web.ifmo/", "deleteItem");
    private final static QName _InsertItemResponse_QNAME = new QName("http://lab1.web.ifmo/", "insertItemResponse");
    private final static QName _FindItem_QNAME = new QName("http://lab1.web.ifmo/", "findItem");
    private final static QName _UpdateItemResponse_QNAME = new QName("http://lab1.web.ifmo/", "updateItemResponse");
    private final static QName _GetItemsResponse_QNAME = new QName("http://lab1.web.ifmo/", "getItemsResponse");
    private final static QName _UpdateItem_QNAME = new QName("http://lab1.web.ifmo/", "updateItem");
    private final static QName _InsertItem_QNAME = new QName("http://lab1.web.ifmo/", "insertItem");
    private final static QName _DeleteItemResponse_QNAME = new QName("http://lab1.web.ifmo/", "deleteItemResponse");
    private final static QName _FindItemResponse_QNAME = new QName("http://lab1.web.ifmo/", "findItemResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ifmo.web.lab1.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateItemResponse }
     * 
     */
    public UpdateItemResponse createUpdateItemResponse() {
        return new UpdateItemResponse();
    }

    /**
     * Create an instance of {@link GetItemsResponse }
     * 
     */
    public GetItemsResponse createGetItemsResponse() {
        return new GetItemsResponse();
    }

    /**
     * Create an instance of {@link DeleteItem }
     * 
     */
    public DeleteItem createDeleteItem() {
        return new DeleteItem();
    }

    /**
     * Create an instance of {@link GetItems }
     * 
     */
    public GetItems createGetItems() {
        return new GetItems();
    }

    /**
     * Create an instance of {@link FindItem }
     * 
     */
    public FindItem createFindItem() {
        return new FindItem();
    }

    /**
     * Create an instance of {@link InsertItemResponse }
     * 
     */
    public InsertItemResponse createInsertItemResponse() {
        return new InsertItemResponse();
    }

    /**
     * Create an instance of {@link DeleteItemResponse }
     * 
     */
    public DeleteItemResponse createDeleteItemResponse() {
        return new DeleteItemResponse();
    }

    /**
     * Create an instance of {@link FindItemResponse }
     * 
     */
    public FindItemResponse createFindItemResponse() {
        return new FindItemResponse();
    }

    /**
     * Create an instance of {@link InsertItem }
     * 
     */
    public InsertItem createInsertItem() {
        return new InsertItem();
    }

    /**
     * Create an instance of {@link UpdateItem }
     * 
     */
    public UpdateItem createUpdateItem() {
        return new UpdateItem();
    }

    /**
     * Create an instance of {@link Item }
     * 
     */
    public Item createItem() {
        return new Item();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetItems }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "getItems")
    public JAXBElement<GetItems> createGetItems(GetItems value) {
        return new JAXBElement<GetItems>(_GetItems_QNAME, GetItems.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "deleteItem")
    public JAXBElement<DeleteItem> createDeleteItem(DeleteItem value) {
        return new JAXBElement<DeleteItem>(_DeleteItem_QNAME, DeleteItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "insertItemResponse")
    public JAXBElement<InsertItemResponse> createInsertItemResponse(InsertItemResponse value) {
        return new JAXBElement<InsertItemResponse>(_InsertItemResponse_QNAME, InsertItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "findItem")
    public JAXBElement<FindItem> createFindItem(FindItem value) {
        return new JAXBElement<FindItem>(_FindItem_QNAME, FindItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "updateItemResponse")
    public JAXBElement<UpdateItemResponse> createUpdateItemResponse(UpdateItemResponse value) {
        return new JAXBElement<UpdateItemResponse>(_UpdateItemResponse_QNAME, UpdateItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetItemsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "getItemsResponse")
    public JAXBElement<GetItemsResponse> createGetItemsResponse(GetItemsResponse value) {
        return new JAXBElement<GetItemsResponse>(_GetItemsResponse_QNAME, GetItemsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "updateItem")
    public JAXBElement<UpdateItem> createUpdateItem(UpdateItem value) {
        return new JAXBElement<UpdateItem>(_UpdateItem_QNAME, UpdateItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "insertItem")
    public JAXBElement<InsertItem> createInsertItem(InsertItem value) {
        return new JAXBElement<InsertItem>(_InsertItem_QNAME, InsertItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "deleteItemResponse")
    public JAXBElement<DeleteItemResponse> createDeleteItemResponse(DeleteItemResponse value) {
        return new JAXBElement<DeleteItemResponse>(_DeleteItemResponse_QNAME, DeleteItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://lab1.web.ifmo/", name = "findItemResponse")
    public JAXBElement<FindItemResponse> createFindItemResponse(FindItemResponse value) {
        return new JAXBElement<FindItemResponse>(_FindItemResponse_QNAME, FindItemResponse.class, null, value);
    }

}
