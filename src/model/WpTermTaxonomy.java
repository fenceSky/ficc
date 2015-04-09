package model;

/**
 * WpTermTaxonomy entity. @author MyEclipse Persistence Tools
 */

public class WpTermTaxonomy implements java.io.Serializable {

	// Fields

	private Long termTaxonomyId;
	private Long termId;
	private String taxonomy;
	private String description;
	private Long parent;
	private Long count;

	// Constructors

	/** default constructor */
	public WpTermTaxonomy() {
	}

	/** full constructor */
	public WpTermTaxonomy(Long termId, String taxonomy, String description,
			Long parent, Long count) {
		this.termId = termId;
		this.taxonomy = taxonomy;
		this.description = description;
		this.parent = parent;
		this.count = count;
	}

	// Property accessors

	public Long getTermTaxonomyId() {
		return this.termTaxonomyId;
	}

	public void setTermTaxonomyId(Long termTaxonomyId) {
		this.termTaxonomyId = termTaxonomyId;
	}

	public Long getTermId() {
		return this.termId;
	}

	public void setTermId(Long termId) {
		this.termId = termId;
	}

	public String getTaxonomy() {
		return this.taxonomy;
	}

	public void setTaxonomy(String taxonomy) {
		this.taxonomy = taxonomy;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getParent() {
		return this.parent;
	}

	public void setParent(Long parent) {
		this.parent = parent;
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}